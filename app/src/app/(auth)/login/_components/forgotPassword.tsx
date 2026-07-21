import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  OutlinedInput,
} from "@mui/material";

interface ForgotPasswordProps {
  open: boolean;
  handleClose: (open: boolean) => void;
}

export default function ForgotPassword({
  open,
  handleClose,
}: ForgotPasswordProps) {
  return (
    <Dialog
      open={open}
      onClose={() => handleClose(false)}
      slotProps={{
        paper: {
          component: "form",
          onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            handleClose(false);
          },
          sx: { backgroundImage: "none" },
        },
      }}
    >
      <DialogTitle>Recuperar senha</DialogTitle>
      <DialogContent
        sx={{ display: "flex", flexDirection: "column", gap: 2, width: "100%" }}
      >
        <DialogContentText>
          Insira o e-mail da sua conta e enviaremos um link para redefinir sua
          senha.
        </DialogContentText>
        <OutlinedInput
          autoFocus
          required
          margin="dense"
          id="email"
          name="email"
          placeholder="Endereço de e-mail"
          type="email"
          fullWidth
        />
      </DialogContent>
      <DialogActions sx={{ pb: 3, px: 3 }}>
        <Button onClick={() => handleClose(false)}>Cancelar</Button>
        <Button variant="contained" type="submit">
          Enviar
        </Button>
      </DialogActions>
    </Dialog>
  );
}
