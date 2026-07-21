import { ProductFormData } from "@/shared/types/types";
import { Box, IconButton, TableCell, TableRow } from "@mui/material";
import { Delete, Edit } from "@mui/icons-material";

type Props = {
    item: ProductFormData;
    onEdit: (item: ProductFormData) => void;
    onDelete: (item: ProductFormData) => void;
};

export default function ProductTableItem({ item, onDelete, onEdit }: Props) {
    return (
        <TableRow hover>
            <TableCell>{item.id}</TableCell>
            <TableCell>
                <Box
                    component="img"
                    src={item.image?.urlImage}
                    alt={item.name}
                    sx={{
                        width: 100,
                        height: 100,
                        objectFit: "cover",
                        borderRadius: 2,
                    }}
                />
            </TableCell>
            <TableCell>{item.name}</TableCell>
            <TableCell>{ }</TableCell>
            <TableCell>categoria</TableCell>

            <TableCell>
                <IconButton onClick={() => onEdit(item)} size="small" aria-label="Editar produto">
                    <Edit />
                </IconButton>
                <IconButton onClick={() => onDelete(item)} size="small" aria-label="Excluir produto">
                    <Delete />
                </IconButton>
            </TableCell>
        </TableRow>
    );
}
