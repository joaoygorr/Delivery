import { z } from "zod";

const createAuthFormSchema = z.object({
    email: z.string()
        .nonempty("O e-mail é obrigatório")
        .email("Formato de e-mail inválido"),
    password: z.string()
        .min(5, "A senha precisa de no mínimo 5 caracteres")
        .optional(),
    confirmPassword: z.string()
        .min(5, "A senha precisa de no mínimo 5 caracteres")
        .optional(),
}).refine((data) => !data.confirmPassword || data.password === data.confirmPassword, {
    message: "A senha e a confirmação de senha precisam ser iguais",
    path: ["confirmPassword"]
});

export default createAuthFormSchema;