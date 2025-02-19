import HeaderAdmin from "@/shared/components/admin/headerAdmin/headerAdmin";
import { Container } from "@mui/material";
import { ReactNode } from "react";

type Props = {
  children: ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <body style={{ margin: 0 }}>
      <HeaderAdmin />
      <Container component="section" maxWidth="lg">
        {children}
      </Container>
    </body>
  );
}
