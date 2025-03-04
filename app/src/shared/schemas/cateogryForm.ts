import { z } from "zod";

const createCategoryFormSchema = z.object({
    idCategory: z.number().optional(),
    name: z.string()
        .trim()
        .nonempty("O nome da categoria é obrigatória")
        .transform(name => {
            return name.trim().split(" ").map(word => {
                return word[0].toLocaleUpperCase().concat(word.substring(1));
            }).join(" ");
        })
})

export default createCategoryFormSchema;