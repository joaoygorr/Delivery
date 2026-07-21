"use client";
import { CategoryFormData } from "@/shared/types/types";
import { Delete, Edit } from "@mui/icons-material";
import { IconButton, TableCell, TableRow } from "@mui/material";

type Props = {
  item: CategoryFormData;
  onEdit: (item: CategoryFormData) => void;
  onDelete: (item: CategoryFormData) => void;
};

export default function CategoryTableItem({ item, onDelete, onEdit }: Props) {
  return (
    <TableRow hover>
      <TableCell>{item.id}</TableCell>
      <TableCell>{item.name}</TableCell>

      <TableCell sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
        <IconButton onClick={() => onEdit(item)} size="small" aria-label="Editar categoria">
          <Edit />
        </IconButton>
        <IconButton onClick={() => onDelete(item)} size="small" aria-label="Excluir categoria">
          <Delete />
        </IconButton>
      </TableCell>
    </TableRow>
  );
}
