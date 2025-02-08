"use client";
import { Box, Button, TextField, Typography } from "@mui/material";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { authFormData } from "@/shared/types/types";
import "./forgot.scss";

export default function Page() {
  const {
    register,
    handleSubmit,
    formState: { errors, isLoading },
  } = useForm<authFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  function submitForgot(data: authFormData) {
    // TODO: envio do furmolário de login
  }

  return (
    <div>
      <Typography component="p" className="text-forgot">
        Deseja recuperar sua senha?
      </Typography>

      <Box
        component="form"
        className="box-form-forgot"
        onSubmit={handleSubmit(submitForgot)}
      >
        <TextField
          label="Digite seu e-mail"
          fullWidth
          autoFocus
          disabled={isLoading}
          {...register("email")}
        />
        {errors.email && <span>{errors.email.message}</span>}

        <Button
          type="submit"
          variant="contained"
          fullWidth
          disabled={isLoading}
        >
          {isLoading ? "Carregando..." : "Entrar"}
        </Button>
      </Box>
    </div>
  );
}
