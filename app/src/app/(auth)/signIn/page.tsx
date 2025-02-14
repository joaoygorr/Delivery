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
import { authFormData } from "@/shared/types/types";
import { authApi } from "@/shared/api/api";
import Link from "next/link";
import ForgotPassword from "@/shared/components/forgotPassword";
import { useState } from "react";

export default function Page() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<authFormData>({
    resolver: zodResolver(createAuthFormSchema),
  });

  const [open, setOpen] = useState(false);

  function submitLogin(data: authFormData) {
    console.log("hi", data);

    try {
      authApi.login(data);
    } catch (e) {
      console.log(e);
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
            helperText={errors.email?.message}
            autoFocus
            color={errors?.email ? "error" : "primary"}
            variant="outlined"
            {...register("email")}
          />
        </FormControl>

        <FormControl>
          <FormLabel htmlFor="password">Senha</FormLabel>
          <TextField
            type="password"
            placeholder="••••••"
            helperText={errors.password?.message}
            fullWidth
            variant="outlined"
            color={errors?.password ? "error" : "primary"}
            {...register("password")}
          />
        </FormControl>

        <ForgotPassword open={open} handleClose={setOpen} />

        <Button type="submit" variant="contained" fullWidth>
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
          <MuiLink href="/signup" variant="body2" component={Link}>
            Cadastrar-se
          </MuiLink>
        </Typography>
      </Box>
    </Card>
  );
}
