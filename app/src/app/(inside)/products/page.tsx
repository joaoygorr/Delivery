"use client";
import ProductEditDialog from "@/shared/components/admin/productEditDialog/productEditDialog";
import {
  Box,
  Button,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import { useState } from "react";
import "./product.scss";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);

  return (
    <Box className="box-product">
      <Box className="box-header-product">
        <Typography component="h5" variant="h5">
          Produtos
        </Typography>
        <Button onClick={() => setOpenDialog(!openDialog)}>Novo Produto</Button>
      </Box>

      <Table>
        <TableHead>
          <TableRow>
            <TableCell
              sx={{ width: 50, display: { xs: "none", md: "table-cell" } }}
            >
              ID
            </TableCell>
            <TableCell>Image</TableCell>
            <TableCell>Nome</TableCell>
            <TableCell sx={{ display: { xs: "none", md: "table-cell" } }}>
              Preço
            </TableCell>
            <TableCell sx={{ display: { xs: "none", md: "table-cell" } }}>
              Categoria
            </TableCell>
            <TableCell sx={{ xs: 50, md: 130 }}>Ações</TableCell>
          </TableRow>
        </TableHead>

        <TableBody></TableBody>
      </Table>

      <ProductEditDialog
        open={openDialog}
        onClose={() => setOpenDialog(false)}
      />
    </Box>
  );
}
