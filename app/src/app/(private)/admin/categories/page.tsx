"use client";
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
import "./category.scss";
import { useState } from "react";

export default function Page() {
  const [openDialog, setOpenDialog] = useState(false);

  return (
    <Box className="box-category">
      <Box className="box-header-category">
        <Typography component="h5" variant="h5">
          Categorias
        </Typography>
        <Button onClick={() => setOpenDialog(!openDialog)}>
          Nova Categoria
        </Button>
      </Box>

      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Nome</TableCell>

            <TableCell sx={{ width: 100 }}>Ações</TableCell>
          </TableRow>
        </TableHead>

        <TableBody></TableBody>
      </Table>
    </Box>
  );
}
