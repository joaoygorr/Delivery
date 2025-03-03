import { z } from "zod";

const createCategoryFormSchema = z.object({
    idCategory: z.number().optional(),
    name: z.string().trim().nonempty("O nome da categoria é obrigatória")
})

export default createCategoryFormSchema;