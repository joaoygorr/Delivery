"use client";
import { Alert, Link as MuiLink } from "@mui/material";
import "./forgot.scss";
import Link from "next/link";

export default function Page() {
  return (
    <div>
      <Alert variant="filled" severity="error" sx={{ mt: 3, mb: 3 }}>
        Este link expirou, refaça o procedimento
      </Alert>

      <MuiLink href="/signIn/forgot" component={Link} variant="button">
        Esqueci minha senha
      </MuiLink>
    </div>
  );
}
