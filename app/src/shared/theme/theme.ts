import { createTheme } from "@mui/material/styles";

export const theme = createTheme({
    palette: {
        primary: {
            main: "#1976d2",
        },
        text: {
            primary: "#333",
            secondary: "#555",
        },
    },
    typography: {
        fontFamily: "Roboto, Arial, sans-serif",
    },
});
