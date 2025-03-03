import { z } from "zod";
import createAuthFormSchema from "../authForm";
import createProductFormSchema from "../productForm";
import createCategoryFormSchema from "../cateogryForm";

export type authFormData = z.infer<typeof createAuthFormSchema>;

export type productFormData = z.infer<typeof createProductFormSchema>;

export type categoryFormData = z.infer<typeof createCategoryFormSchema>;