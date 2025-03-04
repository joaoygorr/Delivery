"use client";
import { categoryFormData } from "@/shared/schemas/types/types";
import { Delete, Edit } from "@mui/icons-material";
import { Button, TableCell, TableRow } from "@mui/material";
import "./categoryTable.scss";

type Props = {
  item: categoryFormData;
  onEdit: (item: categoryFormData) => void;
  onDelete: (item: categoryFormData) => void;
};

export default function CategoryTableItem({ item, onDelete, onEdit }: Props) {
  return (
    <TableRow hover>
      <TableCell>{item.idCategory}</TableCell>
      <TableCell>{item.name}</TableCell>

      <TableCell className="box-actions-icons">
        <Button onClick={() => onEdit(item)} size="small">
          <Edit />
        </Button>
        <Button onClick={() => onDelete(item)} size="small">
          <Delete />
        </Button>
      </TableCell>
    </TableRow>
  );
}
