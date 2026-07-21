"use client";
import ProductEditDialog from "@/app/(private)/admin/products/_components/productDialog/productDialog";
import {
  Box,
  Button,
  CircularProgress,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import {
  CategoryFormData,
  ProductFormData,
} from "@/shared/types/types";
import { categoryApi, productApi } from "@/shared/api/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { useQuery } from "@tanstack/react-query";
import { IPagedResponse } from "@/shared/types/IPagedResponse";
import ProductTableItem from "@/app/(private)/admin/products/_components/productTableItems/productTableItem";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState<CategoryFormData[]>([]);
  const [productToEdit, setProductToEdit] = useState<ProductFormData>();

  function teste(data: ProductFormData) {
    if (productToEdit) {
      handleEditProduct(data);
    } else {
      handleSave(data);
    }
  }

  const handleSave = async (data: ProductFormData) => {
    setLoading(true);
  }


  async function handleSaveEditDialog(data: ProductFormData) {
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

  const { data: products, isLoading: isLoadingProducts } = useQuery<IPagedResponse<ProductFormData[]>>({
    queryKey: ["products"],
    queryFn: () => productApi.getProducts(),
  });

  useEffect(() => {
    async function getData() {
      try {
        const response = await categoryApi.getCategories();
        setCategories(response.content);
      } catch (error: unknown) { }
    }
    getData();
  }, []);

  const handleNewProduct = () => {
    setProductToEdit(undefined);
    setOpenDialog(true);
  }

  const handleEditProduct = (product: ProductFormData) => {
    setProductToEdit(product)
    setOpenDialog(true);
  };

  const handleDeleteProduct = async (data: ProductFormData) => {
    if (!data.id) return;

    try {
      await productApi.deleteObject(data.id);
      toast.success("Produto excluída com sucesso");
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
          Produtos
        </Typography>
        <Button onClick={handleNewProduct}>Novo Produto</Button>
      </Box>

      <Table>
        <TableHead sx={{ "& .MuiTableCell-root": { fontWeight: "bold" } }}>
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
            <TableCell
              align="center"
              sx={{ width: { xs: 50, md: 130 } }}
            >
              Ações
            </TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {isLoadingProducts ? (
            <TableRow>
              <TableCell colSpan={6} align="center" sx={{ py: 4 }}>
                <CircularProgress size={30} />
              </TableCell>
            </TableRow>
          ) : (
            products?.content?.map((item) => (
              <ProductTableItem
                key={item.id}
                item={item}
                onDelete={handleDeleteProduct}
                onEdit={handleEditProduct}
              />
            ))
          )}
        </TableBody>
      </Table>

      <ProductEditDialog
        product={productToEdit}
        categories={categories}
        open={openDialog}
        onSave={teste}
        onClose={() => setOpenDialog(false)}
        disable={loading}
      />
    </Box>
  );
}
