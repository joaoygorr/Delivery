import { z } from "zod";
import createAuthFormSchema from "../schemas/authForm";
import createProductFormSchema from "../schemas/productForm";
import createCategoryFormSchema from "../schemas/categoryForm";

export type AuthFormData = z.infer<typeof createAuthFormSchema>;

export type ProductFormData = z.infer<typeof createProductFormSchema>;

export type CategoryFormData = z.infer<typeof createCategoryFormSchema>;
