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
import { useCallback, useEffect, useState } from "react";
import CategoryDialog from "@/shared/components/admin/dialogs/categoryDialog/categoryDialog";
import { categoryFormData } from "@/shared/schemas/types/types";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { categoryApi } from "@/shared/api/api";
import CategoryTableItem from "@/shared/components/admin/items/categoryTableItems/categoryTableItems";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState<categoryFormData[]>([]);
  const [categoryToEdit, setCategoryToEdit] = useState<categoryFormData>();

  function handleSaveEditDialog(data: categoryFormData) {
    if (categoryToEdit) {
      handleEdit(data);
    } else {
      handleSave(data);
    }
  }

  const getData = useCallback(async () => {
    try {
      const { data } = await categoryApi.getCategories();
      setCategories(data?.content);
    } catch (error) {
      if (error instanceof AxiosError) {
        toast.error(error.message);
      }
    }
  }, []);

  useEffect(() => {
    getData();
  }, [getData]);

  const handleSave = async (data: categoryFormData) => {
    setLoading(true);
    try {
      const newCategory = await categoryApi.createCategory(data);
      setCategories((prev) => [...prev, newCategory.data as categoryFormData]);

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

  const handleEdit = async (data: categoryFormData) => {
    setLoading(true);
    try {
      const updatedCategory = await categoryApi.updateCategory(data);

      setCategories((prev) =>
        prev.map((cat) =>
          cat.idCategory === updatedCategory.data.idCategory
            ? updatedCategory.data
            : cat
        )
      );
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
      setCategories((prev) =>
        prev.filter((cat) => cat.idCategory !== data.idCategory)
      );
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
            <TableCell>Nome da categoria</TableCell>

            <TableCell className="box-actions">Ações</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {!loading &&
            categories.map((item) => (
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
