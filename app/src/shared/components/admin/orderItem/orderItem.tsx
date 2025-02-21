import { Box, Button, MenuItem, Select, Typography } from "@mui/material";

export default function OrderItem() {
  return (
    <Box sx={{ border: "1px solid #eee", color: "#fff", borderRadius: 2 }}>
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          p: 1,
        }}
      >
        <Box>
          <Typography component="p">orderDate</Typography>
          <Typography component="p">username</Typography>
          <Button size="small" sx={{ color: "#fff", p: 0 }}>
            imprimir
          </Button>
        </Box>
        <Box>
          <Typography component="p" sx={{ fontSize: 24 }}>
            #id
          </Typography>
        </Box>
        <Box sx={{ backgroundColor: "#eee", p: 1 }}>
          <Select
            variant="standard"
            // value={item.status}
            fullWidth
          >
            <MenuItem value="preparing">Preparando</MenuItem>
            <MenuItem value="sent">Enviado</MenuItem>
            <MenuItem value="delivered">Entregue</MenuItem>
          </Select>
        </Box>
      </Box>
    </Box>
  );
}
