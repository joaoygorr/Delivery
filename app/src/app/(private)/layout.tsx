"use client";
import HeaderAdmin from "@/shared/components/headerAdmin/headerAdmin";
import { Container } from "@mui/material";
import { ThemeProvider } from "@mui/material/styles";
import { ReactNode } from "react";
import { QueryClientProvider } from "@tanstack/react-query";
import { queryClient } from "@/shared/api/queryClient";
import { theme } from "@/shared/theme/theme";

type Props = {
  children: ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={theme}>
        <HeaderAdmin />
        <Container component="section" maxWidth="lg">
          {children}
        </Container>
      </ThemeProvider>
    </QueryClientProvider>
  );
}
