"use client";
import {
  Box,
  Button,
  CircularProgress,
  TextField,
  Typography,
  Link as MuiLink,
  Card,
  FormControl,
  FormLabel,
  Divider,
} from "@mui/material";
import { useForm } from "react-hook-form";
import createAuthFormSchema from "@/shared/schemas/authForm";
import { zodResolver } from "@hookform/resolvers/zod";
import { authApi } from "@/shared/api/api";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { AuthFormData } from "@/shared/types/types";
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
  } = useForm<AuthFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  async function submitRegister(data: AuthFormData) {
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
    <Card
      variant="outlined"
      sx={{
        display: "flex",
        flexDirection: "column",
        alignSelf: "center",
        width: "100%",
        p: 4,
        gap: 2,
        m: "auto",
        boxShadow:
          "hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px",
        fontFamily: "Inter, sans-serif",
      }}
    >
      <Typography
        component="h1"
        variant="h4"
        sx={{ color: "#555", width: "100%", fontSize: "clamp(2rem, 10vw, 2.15rem)" }}
      >
        Cadastrar-se
      </Typography>

      <Box
        component="form"
        noValidate
        onSubmit={handleSubmit(submitRegister)}
        sx={{ display: "flex", flexDirection: "column", gap: 2 }}
      >
        <FormControl>
          <FormLabel htmlFor="userName" sx={{ fontSize: ".875rem", mb: 0.5 }}>
            Nome Completo
          </FormLabel>
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
          <FormLabel htmlFor="email" sx={{ fontSize: ".875rem", mb: 0.5 }}>
            E-mail
          </FormLabel>
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
          <FormLabel htmlFor="password" sx={{ fontSize: ".875rem", mb: 0.5 }}>
            Senha
          </FormLabel>
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
          <FormLabel htmlFor="confirmPassord" sx={{ fontSize: ".875rem", mb: 0.5 }}>
            Confirme sua senha
          </FormLabel>
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
          {loading ? <CircularProgress size={20} color="inherit" /> : "registrar"}
        </Button>
      </Box>

      <Divider sx={{ color: "hsl(220, 20%, 35%)" }}>
        <Typography>ou</Typography>
      </Divider>

      <Box sx={{ textAlign: "center" }}>
        <Typography sx={{ fontWeight: 400, fontSize: ".875rem", lineHeight: 1.5 }}>
          Já tenho uma conta{" "}
          <MuiLink
            href="/login"
            variant="body2"
            component={Link}
            sx={{ fontWeight: 500, color: "hsl(220, 30%, 6%)", lineHeight: 1.43 }}
          >
            Login
          </MuiLink>
        </Typography>
      </Box>
    </Card>
  );
}
