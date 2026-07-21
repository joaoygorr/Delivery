import { productFormData } from "@/shared/types/types";
import { Box, Button, TableCell, TableRow } from "@mui/material";
import { Delete, Edit } from "@mui/icons-material";

type Props = {
    item: productFormData;
    onEdit: (item: productFormData) => void;
    onDelete: (item: productFormData) => void;
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