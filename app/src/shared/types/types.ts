import { z } from "zod";
import createAuthFormSchema from "../schemas/authForm";
import createProductFormSchema from "../schemas/productForm";
import createCategoryFormSchema from "../schemas/categoryForm";

export type authFormData = z.infer<typeof createAuthFormSchema>;

export type productFormData = z.infer<typeof createProductFormSchema>;

export type categoryFormData = z.infer<typeof createCategoryFormSchema>;