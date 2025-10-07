"use client";
import {
  Box,
  Button,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import "./category.scss";
import { useState } from "react";
import CategoryDialog from "@/shared/components/admin/dialogs/categoryDialog/categoryDialog";
import { categoryFormData } from "@/shared/schemas/types/types";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { categoryApi } from "@/shared/api/api";
import CategoryTableItem from "@/shared/components/admin/items/categoryTableItems/categoryTableItems";
import { useQuery } from "@tanstack/react-query";
import { IPagedResponse } from "@/shared/schemas/types/IPagedResponse";
import { useCategoryMutations } from "@/shared/hooks/useCategoryMutations";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);
  const [loading, setLoading] = useState(false);
  const [categoryToEdit, setCategoryToEdit] = useState<categoryFormData>();
  const { createCategoryMutation } = useCategoryMutations();

  function handleSaveEditDialog(data: categoryFormData) {
    if (categoryToEdit) {
      handleEdit(data);
    } else {
      handleSave(data);
    }
  }

  const { data: categories } = useQuery<IPagedResponse<categoryFormData[]>>({
    queryKey: ["categories"],
    queryFn: () => categoryApi.getCategories(),
  });

  const handleSave = async (data: categoryFormData) => {
    setLoading(true);
    try {
      await createCategoryMutation.mutateAsync(data);
      toast.success("Categoria criada com sucesso!");
      setOpenDialog(false);
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = async (data: categoryFormData) => {
    setLoading(true);
    try {
      await categoryApi.updateCategory(data);
      toast.success("Categoria criada com sucesso!");
      setOpenDialog(false);
    } catch (error) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.error || "Erro na requisição.");
      }
    } finally {
      setLoading(false);
    }
  };

  const handleNewCategory = () => {
    setCategoryToEdit(undefined);
    setOpenDialog(true);
  };

  const handleEditCategory = (category: categoryFormData) => {
    setCategoryToEdit(category);
    setOpenDialog(true);
  };

  const handleDeleteCategory = async (data: categoryFormData) => {
    if (!data?.idCategory) return;

    try {
      await categoryApi.deleteObject(data.idCategory);
      toast.success("Categoria excluída com sucesso");
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        toast.error(error.message);
      }
    }
  };

  return (
    <Box className="box-category">
      <Box className="box-header-category">
        <Typography component="h5" variant="h5">
          Categorias
        </Typography>
        <Button onClick={handleNewCategory}>Nova Categoria</Button>
      </Box>

      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Categoria</TableCell>

            <TableCell className="box-actions">Ações</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {categories?.content?.map((item) => (
            <CategoryTableItem
              key={item.idCategory}
              item={item}
              onDelete={handleDeleteCategory}
              onEdit={handleEditCategory}
            />
          ))}
        </TableBody>
      </Table>

      <CategoryDialog
        onSave={handleSaveEditDialog}
        open={openDialog}
        onClose={() => setOpenDialog(false)}
        disable={loading}
        category={categoryToEdit}
      />
    </Box>
  );
}
