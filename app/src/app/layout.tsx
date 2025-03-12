import { Metadata } from "next";
import { Flip, ToastContainer } from "react-toastify";

export const metadata: Metadata = {
  title: "Create Next App",
  description: "Generated by create next app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <body style={{ margin: 0 }}>
        {children}
        <ToastContainer
          position="top-right"
          autoClose={2000}
          theme="colored"
          transition={Flip}
          pauseOnHover={false}
          pauseOnFocusLoss={false}
        />
      </body>
    </html>
  );
}
