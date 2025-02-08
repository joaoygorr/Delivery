"use client";
import { Box, Button, TextField, Typography } from "@mui/material";
import "./signIn.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { authFormData } from "@/shared/types/types";

export default function Page() {
  const {
    register,
    handleSubmit,
    formState: { errors, isLoading },
  } = useForm<authFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  function submitConfirm(data: authFormData) {
    // TODO: envio do furmolário de login
  }

  return (
    <div>
      <Typography component="p" className="text-auth">
        Olá **user**, defnia sua nova senha abaixo
      </Typography>

      <Box
        component="form"
        className="box-form-confirm"
        onSubmit={handleSubmit(submitConfirm)}
      >
        <TextField
          label="Digite sua nova senha"
          type="password"
          fullWidth
          disabled={isLoading}
          {...register("password")}
        />
        {errors.password && <span>{errors.password.message}</span>}

        <TextField
          label="Confirme sua nova senha"
          type="password"
          fullWidth
          disabled={isLoading}
          {...register("confirmPassword")}
        />
        {errors.password && <span>{errors.password.message}</span>}

        <Button
          type="submit"
          variant="contained"
          fullWidth
          disabled={isLoading}
        >
          {isLoading ? "Carregando..." : "definir nova senha"}
        </Button>
      </Box>
    </div>
  );
}
