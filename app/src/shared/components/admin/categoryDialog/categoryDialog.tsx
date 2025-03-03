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

type Props = {
  open: boolean;
  onClose: () => void;
  onSave: (event: categoryFormData) => void;
  disable: boolean;
};

export default function CategoryDialog({
  disable,
  onClose,
  onSave,
  open,
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
    if (!open) reset();
  }, [open, reset]);

  return (
    <Dialog open={open} onClose={onClose} fullWidth>
      <DialogTitle>Nova Categoria</DialogTitle>
      <DialogContent>
        <Box
          component="form"
          encType="multipart/form-data"
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
