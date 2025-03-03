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
import "./productDialog.scss";
import { useEffect, useState } from "react";
import { productFormData } from "@/shared/schemas/types/types";
import handlePriceChange from "@/shared/utils/priceMask";

type Props = {
  open: boolean;
  onClose: () => void;
  onSave: (event: productFormData) => void;
  disable: boolean;
};

export default function ProductDialog({
  open,
  onClose,
  onSave,
  disable,
}: Props) {
  const [uploadMethod, setUploadMethod] = useState<string>("file");

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<productFormData>({
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
          className="box-form-product"
        >
          <FormControl>
            <FormLabel>Escolha como deseja enviar a imagem</FormLabel>
            <RadioGroup
              value={uploadMethod}
              onChange={(e) => setUploadMethod(e.target.value)}
              className="radio"
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
              disabled={disable}
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
              disabled={disable}
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
              error={!!errors.categoryId}
              {...register("categoryId")}
            >
              <MenuItem value="1">teste</MenuItem>
            </Select>
            {errors.categoryId && (
              <FormHelperText>{errors.categoryId.message}</FormHelperText>
            )}
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
