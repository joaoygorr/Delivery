"use client";
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
import { useState } from "react";
import CategoryDialog from "@/app/(private)/admin/categories/_components/categoryDialog/categoryDialog";
import { CategoryFormData } from "@/shared/types/types";
import { categoryApi } from "@/shared/api/api";
import CategoryTableItem from "@/app/(private)/admin/categories/_components/categoryTableItems/categoryTableItems";
import { useQuery } from "@tanstack/react-query";
import { IPagedResponse } from "@/shared/types/IPagedResponse";
import { useCategoryMutations } from "@/app/(private)/admin/categories/_hooks/useCategoryMutations";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);
  const [loading, setLoading] = useState(false);
  const [categoryToEdit, setCategoryToEdit] = useState<CategoryFormData>();
  const { createCategoryMutation, updateCategoryMutation,
    deleteCategoryMutation
  } = useCategoryMutations();

  function handleSaveEditDialog(data: CategoryFormData) {
    if (categoryToEdit) {
      handleEdit(data);
    } else {
      handleSave(data);
    }
  }

  const { data: categories, isLoading: isLoadingCategories } = useQuery<IPagedResponse<CategoryFormData[]>>({
    queryKey: ["categories"],
    queryFn: () => categoryApi.getCategories(),
  });

  const handleSave = async (data: CategoryFormData) => {
    setLoading(true);
    try {
      await createCategoryMutation.mutateAsync(data);
      setOpenDialog(false);
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = (data: CategoryFormData) => {
    updateCategoryMutation.mutate(data, {
      onSuccess: () => {
        setOpenDialog(false);
      },
    });
  };

  const handleNewCategory = () => {
    setCategoryToEdit(undefined);
    setOpenDialog(true);
  };

  const handleEditCategory = (category: CategoryFormData) => {
    setCategoryToEdit(category);
    setOpenDialog(true);
  };

  const handleDeleteCategory = async (data: CategoryFormData) => {
    if (!data.id) return;
    await deleteCategoryMutation.mutateAsync(data.id);
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
          {isLoadingCategories ? (
            <TableRow>
              <TableCell colSpan={3} align="center" sx={{ py: 4 }}>
                <CircularProgress size={30} />
              </TableCell>
            </TableRow>
          ) : (
            categories?.content?.map((item) => (
              <CategoryTableItem
                key={item.id}
                item={item}
                onDelete={handleDeleteCategory}
                onEdit={handleEditCategory}
              />
            ))
          )}
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
