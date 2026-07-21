"use client";
import { ReactNode } from "react";
import { Box, Container, Typography } from "@mui/material";
import { ThemeProvider } from "@mui/material/styles";
import { theme } from "@/shared/theme/theme";

type Props = {
  children: ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <Box sx={{ mt: 4, display: "flex", flexDirection: "column", alignItems: "center" }}>
          <Typography component="h3" variant="h3">
            Delivery
          </Typography>
          <Typography component="h5" variant="h5">
            Painel do estabelecimento
          </Typography>
          {children}
        </Box>
      </Container>
    </ThemeProvider>
  );
}
