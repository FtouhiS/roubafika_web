<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Product;
use App\Form\ProductType;
use App\Repository\ProductRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/admin/product')]
class ProductController extends AbstractController
{
    #[Route('/', name: 'app_product_index', methods: ['GET'])]
    public function index(ProductRepository $productRepository): Response
    {
        return $this->render('product/index.html.twig', [
            'products' => $productRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_product_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ProductRepository $productRepository): Response
    {
        $product = new Product();
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $productRepository->save($product, true);

            return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('product/new.html.twig', [
            'product' => $product,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_product_show', methods: ['GET'])]
    public function show(Product $product): Response
    {
        return $this->render('product/show.html.twig', [
            'product' => $product,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_product_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Product $product, ProductRepository $productRepository): Response
    {
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $productRepository->save($product, true);

            return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('product/edit.html.twig', [
            'product' => $product,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_product_delete', methods: ['POST'])]
    public function delete(Request $request, Product $product, ProductRepository $productRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$product->getId(), $request->request->get('_token'))) {
            $productRepository->remove($product, true);
        }

        return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/product/add-to-basket/{id}", name="app_product_add_to_basket")
     */
    public function addToBasket(Product $produit, SessionInterface $session): Response
    {
        $panier = $session->get('panier', []);

        if (!array_key_exists($produit->getId(), $panier)) {
            $panier[$produit->getId()] = [
                'id' => $produit->getId(),
                'nomProduit' => $produit->getNomProduit(),
                'prix' => $produit->getPrice(),
                'quantite' => 1,
            ];
        } else {
            $panier[$produit->getId()]['quantite']++;
        }

        $session->set('panier', $panier);

        return $this->redirectToRoute('app_product_index');
    }

    /**
     * @Route("/product/{id}/command", name="app_product_command", methods={"POST"})
     */
    public function command(Request $request, Product $product, SessionInterface $session): Response
    {
        // Logic to add the product to the user's orders table or basket
        // ...

        return $this->redirectToRoute('app_product_index');
    }

    #[Route('/command/{id}', name: 'app_product_command')]
    public function commandProduct(Product $product, EntityManagerInterface $entityManager): Response
    {
        $user = $this->getUser(); // replace this with your own user retrieval logic

        // create a new Commande object and set its fields
        $commande = new Commande();
        $commande->setDate(new \DateTime());
        $commande->setUser($user);
        $commande->setTotal($product->getPrice());

        // add the clicked product to the Commande object
        $commande->addProduit($product);

        // save the new Commande object to the database
        $entityManager->persist($commande);
        $entityManager->flush();

        return $this->redirectToRoute('app_product_index');
    }
}
