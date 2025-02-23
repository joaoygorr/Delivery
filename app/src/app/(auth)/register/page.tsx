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

export default function Page() {
  const router = useRouter();

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
      router.push("/products");
    } catch {}
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
            {...register("email")}
            color={errors?.email ? "error" : "primary"}
            variant="outlined"
            helperText={errors.email?.message}
          />
        </FormControl>

        <FormControl>
          <FormLabel htmlFor="password">Senha</FormLabel>
          <TextField
            placeholder="••••••"
            type="password"
            variant="outlined"
            fullWidth
            {...register("password")}
            color={errors?.password ? "error" : "primary"}
            helperText={errors.password?.message}
          />
        </FormControl>

        <FormControl>
          <FormLabel htmlFor="confirmPassord">Confirme sua senha</FormLabel>
          <TextField
            type="password"
            placeholder="••••••"
            fullWidth
            {...register("confirmPassword")}
            color={errors?.confirmPassword ? "error" : "primary"}
            variant="outlined"
            helperText={errors.confirmPassword?.message}
          />
        </FormControl>

        <Button type="submit" variant="contained" fullWidth>
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
