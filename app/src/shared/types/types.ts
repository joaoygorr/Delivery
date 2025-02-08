import { z } from "zod";
import createAuthFormSchema from "../schemas/authForm";

export type authFormData = z.infer<typeof createAuthFormSchema>;
