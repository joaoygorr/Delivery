import { z } from "zod";

const createProductFormSchema = z.object({
    idProduct: z.number().optional(),
    name: z.string()
        .trim()
        .nonempty("O nome do produto é obrigatório"),
    img: z.union([
        z.instanceof(FileList)
            .transform(list => list.item(0)),
        z.string().trim().url("URL inválida")
    ]),
    price: z.string()
        .trim()
        .regex(/^R\$\s?\d{1,3}(\.\d{3})*(,\d{2})$/, "Preço inválido"),
    description: z.string()
        .trim(),
    categoryId: z.string()
        .trim()
        .nonempty("A categoria é obrigatória")
})

export default createProductFormSchema; 