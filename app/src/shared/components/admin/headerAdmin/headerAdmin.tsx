"use client";
import {
  AppBar,
  Box,
  Button,
  IconButton,
  Toolbar,
  Typography,
} from "@mui/material";
import Link from "next/link";
import "./header.scss";
import { Menu } from "@mui/icons-material";
import HeaderDrawer from "./headerDrawer/headerDrawer";

export default function HeaderAdmin() {
  const handleLogout = () => {};

  const handleDrawerToggle = () => {};

  return (
    <div>
      <AppBar component="nav" position="relative">
        <Toolbar>
          <IconButton
            color="inherit"
            edge="start"
            sx={{ display: { sm: "none" } }}
            onClick={handleDrawerToggle}
          >
            <Menu />
          </IconButton>

          <Typography
            component="div"
            variant="h6"
            sx={{ flexGrow: 1, display: { xs: "none", sm: "block" } }}
          >
            <Link href="/" className="link">
              Painel
            </Link>
          </Typography>

          <Box
            sx={{ display: { xs: "none", sm: "block" } }}
            className="box-nav"
          >
            <Link href="/orders">
              <Button>pedidos</Button>
            </Link>

            <Link href="/products">
              <Button>produtos</Button>
            </Link>

            <Link href="/categories">
              <Button>categorias</Button>
            </Link>
            <Button onClick={handleLogout}>sair</Button>
          </Box>
        </Toolbar>
      </AppBar>
      <Box component="nav">{/* <HeaderDrawer /> */}</Box>
    </div>
  );
}
