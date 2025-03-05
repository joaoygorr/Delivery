"use client";
import createCategoryFormSchema from "@/shared/schemas/cateogryForm";
import { categoryFormData } from "@/shared/schemas/types/types";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  Box,
  Button,
  Dialog,
  DialogContent,
  DialogTitle,
  FormControl,
  FormLabel,
  TextField,
} from "@mui/material";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import "./categoryDialog.scss";

type Props = {
  open: boolean;
  onClose: () => void;
  onSave: (event: categoryFormData) => void;
  disable: boolean;
  category?: categoryFormData;
};

export default function CategoryDialog({
  disable,
  onClose,
  onSave,
  open,
  category,
}: Props) {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<categoryFormData>({
    resolver: zodResolver(createCategoryFormSchema),
  });

  useEffect(() => {
    if (open) reset(category || { name: "" });
  }, [open, reset, category]);

  return (
    <Dialog open={open} onClose={onClose} fullWidth>
      <DialogTitle>
        {category ? "Editar Categoria" : "Nova Categoria"}
      </DialogTitle>
      <DialogContent>
        <Box
          component="form"
          onSubmit={handleSubmit(onSave)}
          className="box-form-category"
        >
          <FormControl>
            <FormLabel htmlFor="nameField">Nome</FormLabel>
            <TextField
              id="nameField"
              variant="standard"
              placeholder="Digite o nome da categoria"
              fullWidth
              disabled={disable}
              defaultValue={category?.name}
              color={errors?.name ? "error" : "primary"}
              helperText={errors.name?.message}
              {...register("name")}
            />
          </FormControl>

          <Box className="box-form-button">
            <Button onClick={onClose} disabled={disable}>
              Cancelar
            </Button>
            <Button type="submit" disabled={disable}>
              Salvar
            </Button>
          </Box>
        </Box>
      </DialogContent>
    </Dialog>
  );
}
