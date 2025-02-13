"use client";
import {
  Box,
  Button,
  TextField,
  Typography,
  Link as MuiLink,
} from "@mui/material";
import { useForm } from "react-hook-form";
import "./signUp.scss";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { zodResolver } from "@hookform/resolvers/zod";
import { authFormData } from "@/shared/types/types";
import { authApi } from "@/shared/api/api";
import Link from "next/link";

export default function Page() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<authFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  function submitRegister(data: authFormData) {
    try {
      authApi.register(data);
    } catch {}
  }

  return (
    <div>
      <Typography component="p" className="text-auth-register">
        Digite seus dados para se registrar no painel administrativo e gerenciar
        produtos/pedidos.
      </Typography>

      <Box
        component="form"
        className="box-form-register"
        onSubmit={handleSubmit(submitRegister)}
      >
        <TextField
          label="Digite nome"
          fullWidth
          autoFocus
          {...register("userName")}
        />
        {errors.userName && <span>{errors.userName.message}</span>}

        <TextField label="Digite seu e-mail" fullWidth {...register("email")} />
        {errors.email && <span>{errors.email.message}</span>}

        <TextField
          label="Digite sua senha"
          type="password"
          fullWidth
          {...register("password")}
        />
        {errors.password && <span>{errors.password.message}</span>}

        <TextField
          label="Digite sua senha novamente"
          type="password"
          fullWidth
          {...register("confirmPassword")}
        />
        {errors.confirmPassword && (
          <span>{errors.confirmPassword.message}</span>
        )}
        <Button type="submit" variant="contained" fullWidth>
          registrar
        </Button>

        <Box className="box-auth-login">
          <MuiLink href="/signin" variant="body2" component={Link}>
            Realizar login
          </MuiLink>
        </Box>
      </Box>
    </div>
  );
}
