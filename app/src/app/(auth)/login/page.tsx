"use client";
import {
  Box,
  Button,
  TextField,
  Typography,
  Link as MuiLink,
  FormControl,
  FormLabel,
  Card,
  Divider,
} from "@mui/material";
import "./signIn.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { authApi } from "@/shared/api/api";
import Link from "next/link";
import ForgotPassword from "@/shared/components/forgotPassword";
import { useState } from "react";
import { authFormData } from "@/shared/schemas/types/types";
import { useRouter } from "next/navigation";
import { toast } from "react-toastify";
import { AxiosError } from "axios";

export default function Page() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<authFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  const [open, setOpen] = useState(false);
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  async function submitLogin(data: authFormData) {
    setLoading(true);
    try {
      await authApi.login(data);
      toast.success("Login realizado com sucesso!");
      router.push("/admin/products");
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.error || "Erro na requisição.");
      }
    } finally {
      setLoading(false);
    }
  }

  return (
    <Card variant="outlined" className="box-card">
      <Typography component="h1" variant="h4" className="text-auth">
        Login
      </Typography>
      <Box
        component="form"
        className="box-form"
        noValidate
        onSubmit={handleSubmit(submitLogin)}
      >
        <FormControl>
          <FormLabel htmlFor="email">Email</FormLabel>
          <TextField
            placeholder="your@email.com"
            autoComplete="email"
            fullWidth
            autoFocus
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
            type="password"
            placeholder="••••••"
            fullWidth
            variant="outlined"
            disabled={loading}
            helperText={errors.password?.message}
            color={errors?.password ? "error" : "primary"}
            {...register("password")}
          />
        </FormControl>

        <ForgotPassword open={open} handleClose={setOpen} />

        <Button type="submit" variant="contained" fullWidth disabled={loading}>
          entrar
        </Button>

        <MuiLink
          component="button"
          type="button"
          variant="body2"
          className="link-forgot"
          onClick={() => setOpen(true)}
        >
          Esqueceu sua senha?
        </MuiLink>
      </Box>

      <Divider className="divider">
        <Typography>ou</Typography>
      </Divider>

      <Box className="box-auth-forgot">
        <Typography>
          Não tem uma conta?{" "}
          <MuiLink href="/register" variant="body2" component={Link}>
            Cadastrar-se
          </MuiLink>
        </Typography>
      </Box>
    </Card>
  );
}
