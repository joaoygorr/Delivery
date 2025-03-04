import { z } from "zod";

const createProductFormSchema = z.object({
    idProduct: z.number().optional(),
    name: z.string()
        .trim()
        .nonempty("O nome do produto é obrigatório")
        .transform(name => {
            return name.trim().split(" ").map(word => {
                return word[0].toLocaleUpperCase().concat(word.substring(1));
            }).join(" ");
        }),
    img: z.union([
        z.instanceof(FileList)
            .transform(list => list.item(0)),
        z.string().trim().url("URL inválida")
    ]),
    price: z.string()
        .trim()
        .regex(/^R\$\s?\d{1,3}(\.\d{3})*(,\d{2})$/, "Preço inválido"),
    description: z.string()
        .trim()
        .transform(desc => {
            return desc.trim().charAt(0).toLocaleUpperCase() + desc.slice(1);
        }),
    categoryId: z.number().min(1, "A categoria é obrigatória")
})

export default createProductFormSchema; 