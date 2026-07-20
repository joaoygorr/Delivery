"use client";
import HeaderAdmin from "@/shared/components/headerAdmin/headerAdmin";
import { Container } from "@mui/material";
import { ReactNode } from "react";
import { QueryClientProvider } from "@tanstack/react-query";
import { queryClient } from "@/shared/api/queryClient";

type Props = {
  children: ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <QueryClientProvider client={queryClient}>
      <HeaderAdmin />
      <Container component="section" maxWidth="lg">
        {children}
      </Container>
    </QueryClientProvider>
  );
}
