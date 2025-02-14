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
      <DialogTitle>Reset password</DialogTitle>
      <DialogContent
        sx={{ display: "flex", flexDirection: "column", gap: 2, width: "100%" }}
      >
        <DialogContentText>
          Enter your account&apos;s email address, and we&apos;ll send you a
          link to reset your password.
        </DialogContentText>
        <OutlinedInput
          autoFocus
          required
          margin="dense"
          id="email"
          name="email"
          label="Email address"
          placeholder="Email address"
          type="email"
          fullWidth
        />
      </DialogContent>
      <DialogActions sx={{ pb: 3, px: 3 }}>
        <Button onClick={() => handleClose(false)}>Cancel</Button>
        <Button variant="contained" type="submit">
          Continue
        </Button>
      </DialogActions>
    </Dialog>
  );
}
