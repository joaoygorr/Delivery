import { Refresh, Search } from "@mui/icons-material";
import {
  Box,
  Button,
  Grid2,
  InputAdornment,
  TextField,
  Typography,
} from "@mui/material";

export default function Page() {
  return (
    <Box sx={{ my: 3 }}>
      <Box sx={{ display: "flex", justifyContent: "space-between" }}>
        <Box sx={{ display: "flex", alignItems: "center" }}>
          <Typography component="h5" variant="h5" sx={{ color: "#555", mr: 2 }}>
            Pedidos
          </Typography>
          <Button
            sx={{ justifyContent: { xs: "flex-start", md: "center" } }}
            size="small"
          >
            <Refresh />
            <Typography
              component="div"
              sx={{ color: "#555", display: { xs: "none", md: "block" } }}
            >
              Atualizar
            </Typography>
          </Button>
        </Box>

        <TextField
          // value={}
          // onChange={}
          // onKeyUp={}
          variant="standard"
          slotProps={{
            input: {
              endAdornment: (
                <InputAdornment position="end">
                  <Search />
                </InputAdornment>
              ),
            },
          }}
        />
      </Box>

      <Grid2 container spacing={3} columns={{ xs: 1, sm: 2, md: 4 }}></Grid2>
    </Box>
  );
}
