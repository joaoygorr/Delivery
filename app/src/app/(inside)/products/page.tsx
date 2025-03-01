"use client";
import ProductEditDialog from "@/shared/components/admin/productEditDialog/productEditDialog";
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
import "./product.scss";
import { productFormData } from "@/shared/schemas/types/types";
import { productApi } from "@/shared/api/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);
  const [loading, setLoading] = useState(false);

  async function handleSaveEditDialog(data: productFormData) {
    setLoading(true);
    try {
      const formData = new FormData();

      const valueMapper = (value: unknown): string | Blob => {
        switch (true) {
          case String(value).includes("R$"):
            return String(value)
              .replace(/\D/g, "")
              .replace(/(\d{2})$/, ".$1");

          case typeof value === "string":
            return value;

          case typeof value === "object":
            return value instanceof File ? value : JSON.stringify(value);

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
    } catch (error) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.error || "Erro na requisição.");
      }
    } finally {
      setLoading(false);
    }
  }

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
            <TableCell>Nome</TableCell>
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
        open={openDialog}
        onSave={handleSaveEditDialog}
        onClose={() => setOpenDialog(false)}
        disable={loading}
      />
    </Box>
  );
}
