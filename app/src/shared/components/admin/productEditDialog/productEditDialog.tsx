"use client";
import createProductFormSchema from "@/shared/schemas/productForm";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  Box,
  Button,
  Dialog,
  DialogContent,
  DialogTitle,
  FormControl,
  FormHelperText,
  FormLabel,
  Input,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import { useForm } from "react-hook-form";
import "./productDialog.scss";
import { useEffect } from "react";
import { productFormData } from "@/shared/schemas/types/types";
import handlePriceChange from "@/shared/utils/priceMask";

type Props = {
  open: boolean;
  onClose: () => void;
};

export default function ProductEditDialog({ open, onClose }: Props) {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<productFormData>({
    resolver: zodResolver(createProductFormSchema),
  });

  function submitProduct(data: any) {
    console.log(data);
  }

  useEffect(() => {
    if (!open) reset();
  }, [open, reset]);

  return (
    <Dialog open={open} onClose={onClose} fullWidth>
      <DialogTitle>Novo Produto</DialogTitle>
      <DialogContent>
        <Box
          component="form"
          encType="multipart/form-data"
          onSubmit={handleSubmit(submitProduct)}
          className="box-form-product"
        >
          <FormControl>
            <FormLabel htmlFor="imgField">Imagem</FormLabel>
            <Input
              id="imgField"
              type="file"
              inputProps={{ accept: "image/*" }}
              color={errors?.img ? "error" : "primary"}
              error={!!errors.img}
              fullWidth
              {...register("img")}
            />
            {errors.img && (
              <FormHelperText>{errors.img.message}</FormHelperText>
            )}
          </FormControl>

          <FormControl>
            <FormLabel htmlFor="nameField">Nome</FormLabel>
            <TextField
              id="nameField"
              variant="standard"
              placeholder="Digite o nome do produto"
              fullWidth
              color={errors?.name ? "error" : "primary"}
              helperText={errors.name?.message}
              {...register("name")}
            />
          </FormControl>

          <FormControl>
            <FormLabel htmlFor="priceField">Preço</FormLabel>
            <TextField
              suppressHydrationWarning
              id="priceField"
              variant="standard"
              type="text"
              placeholder="Ex: R$ 19,90"
              fullWidth
              color={errors?.price ? "error" : "primary"}
              {...register("price", {
                onChange: handlePriceChange,
              })}
              helperText={errors.price?.message}
            />
          </FormControl>

          <FormControl>
            <FormLabel htmlFor="descriptionField">Descrição</FormLabel>
            <TextField
              id="descriptionField"
              variant="standard"
              placeholder="Descreva o produto brevemente"
              type="text"
              multiline
              rows={4}
              fullWidth
              color={errors?.description ? "error" : "primary"}
              helperText={errors.description?.message}
              {...register("description")}
            />
          </FormControl>

          <FormControl>
            <FormLabel htmlFor="categoryField">Categoria</FormLabel>
            <Select
              id="categoryField"
              variant="standard"
              fullWidth
              error={!!errors.category}
              {...register("category")}
            >
              <MenuItem value="teste">teste</MenuItem>
            </Select>
            {errors.category && (
              <FormHelperText>{errors.category.message}</FormHelperText>
            )}
          </FormControl>
          <Box className="box-form-button">
            <Button onClick={onClose}>Cancelar</Button>
            <Button type="submit">Salvar</Button>
          </Box>
        </Box>
      </DialogContent>
    </Dialog>
  );
}
