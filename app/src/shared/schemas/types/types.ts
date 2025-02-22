import { z } from "zod";
import createAuthFormSchema from "../authForm";
import createProductFormSchema from "../productForm";

export type authFormData = z.infer<typeof createAuthFormSchema>;

export type productFormData = z.infer<typeof createProductFormSchema>;