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
import { useState } from "react";
import CategoryDialog from "@/app/(private)/admin/categories/_components/categoryDialog/categoryDialog";
import { categoryFormData } from "@/shared/types/types";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { categoryApi } from "@/shared/api/api";
import CategoryTableItem from "@/app/(private)/admin/categories/_components/categoryTableItems/categoryTableItems";
import { useQuery } from "@tanstack/react-query";
import { IPagedResponse } from "@/shared/types/IPagedResponse";
import { useCategoryMutations } from "@/app/(private)/admin/categories/_hooks/useCategoryMutations";

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
    if (!data?.id) return;

    try {
      await categoryApi.deleteObject(data.id);
      toast.success("Categoria excluída com sucesso");
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.error || error.message);
      }
    }
  };

  return (
    <Box sx={{ my: 3 }}>
      <Box sx={{ display: "flex", justifyContent: "space-between", mb: 3 }}>
        <Typography component="h5" variant="h5" sx={{ color: "#555", mr: 2 }}>
          Categorias
        </Typography>
        <Button onClick={handleNewCategory}>Nova Categoria</Button>
      </Box>

      <Table>
        <TableHead sx={{ "& .MuiTableCell-root": { fontWeight: "bold" } }}>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Categoria</TableCell>
            <TableCell sx={{ textAlign: "center", width: 100 }}>Ações</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {categories?.content?.map((item) => (
            <CategoryTableItem
              key={item.id}
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
