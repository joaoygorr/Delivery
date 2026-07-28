"use client";
import createProductFormSchema from "@/shared/schemas/productForm";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  Box,
  Button,
  CircularProgress,
  Dialog,
  DialogContent,
  DialogTitle,
  FormControl,
  FormControlLabel,
  FormHelperText,
  FormLabel,
  Input,
  MenuItem,
  Radio,
  RadioGroup,
  Select,
  TextField,
} from "@mui/material";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import {
  CategoryFormData,
  ProductFormData,
} from "@/shared/types/types";
import FormatValor from "@/shared/utils/priceMask";

type Props = {
  open: boolean;
  onClose: () => void;
  onSave: (event: ProductFormData) => void;
  disable: boolean;
  categories: CategoryFormData[];
  product?: ProductFormData
};

export default function ProductDialog({
  open,
  onClose,
  onSave,
  disable,
  categories,
  product
}: Props) {
  const [uploadMethod, setUploadMethod] = useState<string>("file");

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<ProductFormData>({
    resolver: zodResolver(createProductFormSchema),
  });

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
          onSubmit={handleSubmit(onSave)}
          sx={{ display: "flex", flexDirection: "column", alignSelf: "center", gap: 2 }}
        >
          <FormControl>
            <FormLabel>Escolha como deseja enviar a imagem</FormLabel>
            <RadioGroup
              value={uploadMethod}
              onChange={(e) => setUploadMethod(e.target.value)}
              sx={{ display: "flex", flexDirection: "row", justifyContent: "space-between" }}
            >
              <FormControlLabel
                value="file"
                control={<Radio />}
                label="Upload de Arquivo"
              />
              <FormControlLabel
                value="url"
                control={<Radio />}
                label="Inserir URL da Imagem"
              />
            </RadioGroup>
          </FormControl>

          {uploadMethod === "file" ? (
            <FormControl>
              <FormLabel htmlFor="imgField">Imagem</FormLabel>
              <Input
                id="imgField"
                type="file"
                fullWidth
                disabled={disable}
                inputProps={{ accept: "image/*" }}
                error={!!errors.img}
                color={errors?.img ? "error" : "primary"}
                {...register("img")}
              />
              {errors.img && (
                <FormHelperText>{errors.img.message}</FormHelperText>
              )}
            </FormControl>
          ) : (
            <FormControl>
              <FormLabel htmlFor="imgUrl">URL da Imagem</FormLabel>
              <Input
                id="imgUrl"
                type="text"
                placeholder="Insira a url da imagem"
                fullWidth
                disabled={disable}
                error={!!errors.img}
                color={errors?.img ? "error" : "primary"}
                {...register("img")}
              />
              {errors.img && (
                <FormHelperText>{errors.img.message}</FormHelperText>
              )}
            </FormControl>
          )}

          <FormControl>
            <FormLabel htmlFor="nameField">Nome</FormLabel>
            <TextField
              id="nameField"
              variant="standard"
              placeholder="Digite o nome do produto"
              fullWidth
              defaultValue={product?.name}
              disabled={disable}
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
              defaultValue={product?.price}
              disabled={disable}
              color={errors?.price ? "error" : "primary"}
              {...register("price", {
                onChange: FormatValor,
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
              disabled={disable}
              defaultValue={product?.description}
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
              disabled={disable}
              defaultValue={product?.categoryId ?? ""}
              error={!!errors.categoryId}
              {...register("categoryId")}
            >
              {categories?.map((category) => (
                <MenuItem key={category.id} value={category.id}>
                  {category.name}
                </MenuItem>
              ))}
            </Select>
            {errors.categoryId && (
              <FormHelperText>{errors.categoryId.message}</FormHelperText>
            )}
          </FormControl>
          <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
            <Button onClick={onClose} disabled={disable}>
              Cancelar
            </Button>
            <Button type="submit" disabled={disable}>
              {disable ? <CircularProgress size={20} /> : "Salvar"}
            </Button>
          </Box>
        </Box>
      </DialogContent>
    </Dialog>
  );
}
