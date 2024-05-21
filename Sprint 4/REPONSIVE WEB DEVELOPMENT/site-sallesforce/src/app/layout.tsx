//import ClickerCounter from "../components/ClickerCounter";
import KeyboardNav from "../components/KeyboardNav"; // Import the missing module from the correct file path

import Header from "../components/Header";
import Footer from "../components/Footer";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Salesforce: Uma Empresa Centrada no Cliente",
  description: "Página Web Salesforce",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className="flex flex-col h-screen">
        <KeyboardNav>
        <Header/>
        {children}
        <Footer/>
        </KeyboardNav>
      </body>
    </html>
  );
}
