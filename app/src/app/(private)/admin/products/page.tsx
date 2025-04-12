"use client";
import ProductEditDialog from "@/shared/components/admin/dialogs/productDialog/productDialog";
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
import { useEffect, useState } from "react";
import "./product.scss";
import {
  categoryFormData,
  productFormData,
} from "@/shared/schemas/types/types";
import { categoryApi, productApi } from "@/shared/api/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState<categoryFormData[]>([]);
  const [products, setProducts] = useState<productFormData[]>([]);

  async function handleSaveEditDialog(data: productFormData) {
    setLoading(true);
    try {
      const formData = new FormData();

      const valueMapper = (value: unknown): string | Blob | number => {
        switch (true) {
          case String(value).includes("R$"):
            return String(value)
              .replace(/\D/g, "")
              .replace(/(\d{2})$/, ".$1");

          case typeof value === "string":
            return value;

          case typeof value === "object":
            return value instanceof File ? value : JSON.stringify(value);

          case typeof value === "number":
            return String(value);

          default:
            return "";
        }
      };

      Object.entries(data).forEach(([key, value]) => {
        if (value !== null && value !== undefined) {
          formData.append(key, valueMapper(value));
        }
      });

      await productApi.createProduct(formData);
      toast.success("Produto cadastrado com sucesso!");
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
      } catch (error: unknown) {}
    }
    getData();
  }, []);

  return (
    <Box className="box-product">
      <Box className="box-header-product">
        <Typography component="h5" variant="h5">
          Produtos
        </Typography>
        <Button onClick={() => setOpenDialog(!openDialog)}>Novo Produto</Button>
      </Box>

      <Table>
        <TableHead>
          <TableRow>
            <TableCell
              sx={{ width: 50, display: { xs: "none", md: "table-cell" } }}
            >
              ID
            </TableCell>
            <TableCell>Image</TableCell>
            <TableCell>Nome do Produto</TableCell>
            <TableCell sx={{ display: { xs: "none", md: "table-cell" } }}>
              Preço
            </TableCell>
            <TableCell sx={{ display: { xs: "none", md: "table-cell" } }}>
              Categoria
            </TableCell>
            <TableCell sx={{ xs: 50, md: 130 }}>Ações</TableCell>
          </TableRow>
        </TableHead>

        <TableBody></TableBody>
      </Table>

      <ProductEditDialog
        categories={categories}
        open={openDialog}
        onSave={handleSaveEditDialog}
        onClose={() => setOpenDialog(false)}
        disable={loading}
      />
    </Box>
  );
}
