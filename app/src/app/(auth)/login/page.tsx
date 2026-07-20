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
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { authApi } from "@/shared/api/api";
import Link from "next/link";
import ForgotPassword from "@/app/(auth)/login/_components/forgotPassword";
import { useState } from "react";
import { authFormData } from "@/shared/types/types";
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
    <Card
      variant="outlined"
      sx={{
        display: "flex",
        flexDirection: "column",
        alignSelf: "center",
        width: "100%",
        m: "auto",
        gap: 2,
        p: 4,
        boxShadow:
          "hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px",
        fontFamily: "Inter, sans-serif",
      }}
    >
      <Typography
        component="h1"
        variant="h4"
        sx={{
          width: "100%",
          fontSize: "clamp(2rem, 10vw, 2.15rem)",
          color: "#555",
          fontWeight: 600,
          lineHeight: 1.5,
        }}
      >
        Login
      </Typography>
      <Box
        component="form"
        noValidate
        onSubmit={handleSubmit(submitLogin)}
        sx={{ display: "flex", flexDirection: "column", width: "100%", gap: 2 }}
      >
        <FormControl>
          <FormLabel htmlFor="email" sx={{ fontSize: ".875rem", mb: 0.5 }}>
            Email
          </FormLabel>
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
          <FormLabel htmlFor="password" sx={{ fontSize: ".875rem", mb: 0.5 }}>
            Senha
          </FormLabel>
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
          onClick={() => setOpen(true)}
          sx={{
            alignSelf: "center",
            fontWeight: 500,
            color: "hsl(220, 30%, 6%)",
            fontSize: ".875rem",
            lineHeight: 1.43,
          }}
        >
          Esqueceu sua senha?
        </MuiLink>
      </Box>

      <Divider sx={{ color: "hsl(220, 20%, 35%)" }}>
        <Typography>ou</Typography>
      </Divider>

      <Box sx={{ textAlign: "center" }}>
        <Typography sx={{ fontWeight: 400, fontSize: ".875rem", lineHeight: 1.5 }}>
          Não tem uma conta?{" "}
          <MuiLink
            href="/register"
            variant="body2"
            component={Link}
            sx={{ fontWeight: 500, color: "hsl(220, 30%, 6%)", lineHeight: 1.43 }}
          >
            Cadastrar-se
          </MuiLink>
        </Typography>
      </Box>
    </Card>
  );
}
