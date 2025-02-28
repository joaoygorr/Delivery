"use client";
import {
  Box,
  Button,
  TextField,
  Typography,
  Link as MuiLink,
  Card,
  FormControl,
  FormLabel,
  Divider,
} from "@mui/material";
import { useForm } from "react-hook-form";
import "./signUp.scss";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { zodResolver } from "@hookform/resolvers/zod";
import { authApi } from "@/shared/api/api";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { authFormData } from "@/shared/schemas/types/types";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { useState } from "react";

export default function Page() {
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<authFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  async function submitRegister(data: authFormData) {
    setLoading(true);
    try {
      await authApi.register(data);
      toast.success("Cadastro feito com sucesso!");
      router.push("/products");
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.error || "Erro na requisição.");
      }
    } finally {
      setLoading(false);
    }
  }

  return (
    <Card variant="outlined" className="box-card-register">
      <Typography component="h1" variant="h4" className="text-auth-register">
        Cadastrar-se
      </Typography>

      <Box
        component="form"
        className="box-form-register"
        noValidate
        onSubmit={handleSubmit(submitRegister)}
      >
        <FormControl>
          <FormLabel htmlFor="userName">Nome Completo</FormLabel>
          <TextField
            placeholder="Jon Snow"
            autoComplete="userName"
            fullWidth
            autoFocus
            variant="outlined"
            disabled={loading}
            helperText={errors.userName?.message}
            color={errors?.userName ? "error" : "primary"}
            {...register("userName")}
          />
        </FormControl>

        <FormControl>
          <FormLabel htmlFor="email">E-mail</FormLabel>
          <TextField
            placeholder="your@email.com"
            autoComplete="email"
            fullWidth
            variant="outlined"
            disabled={loading}
            helperText={errors.email?.message}
            color={errors?.email ? "error" : "primary"}
            {...register("email")}
          />
        </FormControl>

        <FormControl>
          <FormLabel htmlFor="password">Senha</FormLabel>
          <TextField
            placeholder="••••••"
            type="password"
            variant="outlined"
            fullWidth
            autoComplete="password"
            disabled={loading}
            helperText={errors.password?.message}
            color={errors?.password ? "error" : "primary"}
            {...register("password")}
          />
        </FormControl>

        <FormControl>
          <FormLabel htmlFor="confirmPassord">Confirme sua senha</FormLabel>
          <TextField
            type="password"
            placeholder="••••••"
            autoComplete="confirmPassword"
            fullWidth
            variant="outlined"
            disabled={loading}
            helperText={errors.confirmPassword?.message}
            color={errors?.confirmPassword ? "error" : "primary"}
            {...register("confirmPassword")}
          />
        </FormControl>

        <Button type="submit" variant="contained" fullWidth disabled={loading}>
          registrar
        </Button>
      </Box>

      <Divider className="divider">
        <Typography>ou</Typography>
      </Divider>

      <Box className="box-auth-login">
        <Typography>
          Já tenho uma conta{" "}
          <MuiLink href="/login" variant="body2" component={Link}>
            Login
          </MuiLink>
        </Typography>
      </Box>
    </Card>
  );
}
