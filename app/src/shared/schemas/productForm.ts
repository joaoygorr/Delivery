import { z } from "zod";

const createProductFormSchema = z.object({
    name: z.string()
        .trim()
        .nonempty("O nome é obrigatório"),
    img: z.instanceof(File, { message: "A imagem deve ser um arquivo válido" }),
    price: z.string()
        .trim()
        .regex(/^R\$\d{1,3}(\.\d{3})*,\d{2}$/, "Preço inválido"),
    description: z.string()
        .trim(),
    category: z.string()
        .trim()
        .nonempty("A categoria é obrigatória")
})

export default createProductFormSchema; 