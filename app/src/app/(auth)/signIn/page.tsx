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

export default function Page() {
  return (
    <div>
      <Typography component="p" className="text-auth">
        Digite seus dados para entrar no painel administrativo e gerenciar
        produtos/pedidos.
      </Typography>

      <Box component="form" className="box-form">
        <TextField
          label="Digite seu e-mail"
          name="email"
          required
          fullWidth
          autoFocus
        />

        <TextField
          label="Digite sua senha"
          name="password"
          type="password"
          required
          fullWidth
        />
        <Button type="submit" variant="contained" fullWidth>
          entrar
        </Button>

        <Box className="box-forgot">
          <MuiLink href="/login/forgot" variant="body2" component={Link}>
            Esqueceu sua senha?
          </MuiLink>
        </Box>
      </Box>
    </div>
  );
}
