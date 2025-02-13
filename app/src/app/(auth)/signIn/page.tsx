"use client";
import {
  Box,
  Button,
  TextField,
  Typography,
  Link as MuiLink,
} from "@mui/material";
import Link from "next/link";
import "./signIn.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { authFormData } from "@/shared/types/types";
import { authApi } from "@/shared/api/api";

export default function Page() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<authFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  function submitLogin(data: authFormData) {
    console.log("hi", data);

    try {
      authApi.login(data);
    } catch (e) {
      console.log(e);
    }
  }

  return (
    <div>
      <Typography component="p" className="text-auth">
        Digite seus dados para entrar no painel administrativo e gerenciar
        produtos/pedidos.
      </Typography>

      <Box
        component="form"
        className="box-form"
        onSubmit={handleSubmit(submitLogin)}
      >
        <TextField
          label="Digite seu e-mail"
          fullWidth
          autoFocus
          {...register("email")}
        />
        {errors.email && <span>{errors.email.message}</span>}

        <TextField
          label="Digite sua senha"
          type="password"
          fullWidth
          {...register("password")}
        />
        {errors.password && <span>{errors.password.message}</span>}

        <Button type="submit" variant="contained" fullWidth>
          entrar
        </Button>

        <Box className="box-auth-forgot">
          <MuiLink href="/signin/forgot" variant="body2" component={Link}>
            Esqueceu sua senha?
          </MuiLink>
        </Box>
        <Box className="box-auth-forgot">
          <MuiLink href="/signup" variant="body2" component={Link}>
            realizar cadastro
          </MuiLink>
        </Box>
      </Box>
    </div>
  );
}
