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
import { useQuery } from "@tanstack/react-query";
import { IPagedResponse } from "@/shared/schemas/types/IPagedResponse";
import ProductTableItem from "@/shared/components/admin/items/productTableItems/productTableItem";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState<categoryFormData[]>([]);
  // const [products, setProducts] = useState<productFormData[]>([]);

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

  const { data: products } = useQuery<IPagedResponse<productFormData[]>>({
    queryKey: ["products"],
    queryFn: () => productApi.getProducts()
  })

  useEffect(() => {
    async function getData() {
      try {
        const response = await categoryApi.getCategories();
        setCategories(response.content);
      } catch (error: unknown) { }
    }
    getData();
  }, []);

  const handleEditCategory = (category: productFormData) => {
    // setCategoryToEdit(category);
    // setOpenDialog(true);
  };

  const handleDeleteCategory = async (data: productFormData) => {
    // if (!data?.id) return;

    // try {
    //   await categoryApi.deleteObject(data.id);
    //   toast.success("Categoria excluída com sucesso");
    // } catch (error: unknown) {
    //   if (error instanceof AxiosError) {
    //     toast.error(error.response?.data?.error || error.message);
    //   }
    // }
  };

  return (
    <Box className="box-product">
      <Box className="box-header-product">
        <Typography component="h5" variant="h5">
          Produtos
        </Typography>
        <Button onClick={() => setOpenDialog(!openDialog)}>Novo Produto</Button>
      </Box>

      <Table>
        <TableHead sx={{
          '& .MuiTableCell-root': {
            fontWeight: 'bold',
          },
        }}>
          <TableRow>
            <TableCell
              sx={{ width: 50, display: { xs: "none", md: "table-cell" } }}
            >
              ID
            </TableCell>
            <TableCell>Image</TableCell>
            <TableCell>Produto</TableCell>
            <TableCell sx={{ display: { xs: "none", md: "table-cell" } }}>
              Preço
            </TableCell>
            <TableCell sx={{ display: { xs: "none", md: "table-cell" } }}>
              Categoria
            </TableCell>
            <TableCell align="center"
              sx={{
                width: {
                  xs: 50,
                  md: 130,
                },
              }}>Ações</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {products?.content?.map((item) => (
            <ProductTableItem
              key={item.id}
              item={item}
              onDelete={handleDeleteCategory}
              onEdit={handleEditCategory}
            />
          ))}
        </TableBody>
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
