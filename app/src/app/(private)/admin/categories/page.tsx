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
import { useEffect, useState } from "react";
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

  async function handleSaveEditDialog(data: categoryFormData) {
    setLoading(true);
    try {
      await categoryApi.createCategory(data);
      toast.success("Categoria criada com sucesso!");
      setOpenDialog(false);
    } catch (error) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.error || "Erro na requisição.");
      }
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    async function getData() {
      try {
        const { data } = await categoryApi.getCategories();
        setCategories(data?.content);
      } catch (error: unknown) {
        if (error instanceof AxiosError) {
          //TODO: fazer
        }
      }
    }
    getData();
  }, []);

  const handleEditCategory = (data: categoryFormData) => {};

  const handleDeleteCategory = (data: categoryFormData) => {};

  return (
    <Box className="box-category">
      <Box className="box-header-category">
        <Typography component="h5" variant="h5">
          Categorias
        </Typography>
        <Button onClick={() => setOpenDialog(!openDialog)}>
          Nova Categoria
        </Button>
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
      />
    </Box>
  );
}
