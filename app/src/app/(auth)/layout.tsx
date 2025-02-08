"use client";
import { ReactNode } from "react";
import "@fontsource/roboto/300.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto/500.css";
import "@fontsource/roboto/700.css";
import { Box, Container, Typography } from "@mui/material";
import "./layout.scss";

type Props = {
  children: ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <Container component="main" maxWidth="xs">
      <Box className="box-layout">
        <Typography component="h3" variant="h3">
          Delivery
        </Typography>
        <Typography component="h5" variant="h5">
          Painel do estabelecimento
        </Typography>
        {children}
      </Box>
    </Container>
  );
}
